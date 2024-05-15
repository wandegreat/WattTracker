package com.example.watttracker;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class calcFragment extends Fragment implements View.OnClickListener {
    private Button btnCalc, btnClear;
    private EditText etKW, etPercent;
    private TextView billResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calc, container, false);

        // Initialize views
        btnCalc = view.findViewById(R.id.btnCalc);
        etKW = view.findViewById(R.id.etKW);
        etPercent = view.findViewById(R.id.etPercent);
        billResult = view.findViewById(R.id.billResult);
        btnCalc.setOnClickListener(this);
        btnClear = view.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        if (view == btnCalc) {
            // Calculate bill amount
            String number2 = etPercent.getText().toString();
            String number1 = etKW.getText().toString();

            double electricityUsed, rebatePercent;
            double price = 0;
            double rebateOff;

            try {
                electricityUsed = Double.parseDouble(number1);
                rebatePercent = Double.parseDouble(number2);

                // Calculate price based on electricity usage
                if (electricityUsed >= 0 && electricityUsed <= 200) {
                    price = electricityUsed * 0.218;
                } else if (electricityUsed >= 201 && electricityUsed <= 300) {
                    price = ((electricityUsed - 200) * 0.334) + (200 * 0.218);
                } else if (electricityUsed >= 301 && electricityUsed <= 600) {
                    price = ((electricityUsed - 300) * 0.516) + (100 * 0.334) + (200 * 0.218);
                } else if (electricityUsed >= 601 && electricityUsed <= 900) {
                    price = ((electricityUsed - 600) * 0.546) + (300 * 0.516) + (100 * 0.334) + (200 * 0.218);
                } else if (electricityUsed > 900) {
                    price = ((electricityUsed - 900) * 0.571) + (300 * 0.546) + (300 * 0.516) + (100 * 0.334) + (200 * 0.218);
                }

                // Apply rebate percentage and display the final price
                if (rebatePercent >= 0 && rebatePercent <= 5) {
                    rebateOff = rebatePercent / 100;
                    double finalPrice = price - (price * rebateOff);
                    String formattedTotal = String.format("RM %.2f", finalPrice);
                    billResult.setText(formattedTotal);
                    billResult.setTextColor(Color.parseColor("#FFDE59"));
                } else {
                    Toast.makeText(requireContext(), "Please enter a rebate percentage between 0 and 5", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException nfe) {
                Toast.makeText(requireContext(), "Wrong inputs. Enter numbers only.", Toast.LENGTH_SHORT).show();
            } catch (Exception exp) {
                Toast.makeText(requireContext(), "Wrong inputs. Enter numbers only.", Toast.LENGTH_SHORT).show();
            }
        } else if (view == btnClear) {
            // Show confirmation dialog before clearing inputs and output
            showConfirmationDialog();
        }
    }

    private void showConfirmationDialog() {
        // Show a confirmation dialog before clearing inputs and output
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Confirmation")
                .setMessage("Are you sure you want to clear?")
                .setPositiveButton("Clear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        etKW.setText("");
                        etPercent.setText("");
                        billResult.setText("RM 0.00");
                        dialog.dismiss();
                        Toast.makeText(requireContext(), "Inputs and output cleared.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}
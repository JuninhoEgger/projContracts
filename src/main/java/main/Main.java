package main;

import entitites.HourContractEntity;
import entitites.WorkerEntity;
import enumeration.WorkerLevemEnum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static java.lang.Enum.valueOf;
import static java.lang.Integer.parseInt;
import static java.util.Calendar.YEAR;
import static java.util.Calendar.getInstance;
import static javax.swing.JOptionPane.*;
import static utils.ValidadeDateUtils.validateDate;

public class Main {
    public static void main(String[] args) {

        final String CONTRACT = "CONTRACT";

        //CONSTRUINDO LISTAS JOPTIONPANE
        Calendar calendar = getInstance();

        List<Integer> dayList = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            dayList.add(i);
        }

        List<Integer> monthList = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            monthList.add(i);
        }

        List<Integer> yearsList = new ArrayList<>();
        for (int i = calendar.get(YEAR); i >= 1900; i--) {
            yearsList.add(i);
        }

        WorkerEntity worker = new WorkerEntity();
        worker.getDepartment().setName(
                showInputDialog(
                        null,
                        "ENTER DEPARTMENT'S NAME"
                ));

        showMessageDialog(null, "ENTER WORKER DATA");
        worker.setName(showInputDialog(null, "NAME"));
        Object[] enums = WorkerLevemEnum.values();
        Object levemEnum = showInputDialog(
                null,
                "LEVEL?",
                "LEVEL",
                QUESTION_MESSAGE,
                null,
                enums,
                enums[0]
        );
        worker.setLevel(valueOf(WorkerLevemEnum.class, levemEnum.toString()));
        worker.setBaseSalary(Double.valueOf(showInputDialog(null, "BASE SALARY")));

        int contractQuantity;
        do {
            contractQuantity = parseInt(
                    showInputDialog(null, "HOW MANY CONTRACTS TO THIS WORKER?")
            );
            if (contractQuantity < 0) {
                showMessageDialog(null, "INVALID CONTRACTS QUANTITY!");
            }
        } while (contractQuantity < 0);

        for (int i = 0; i < contractQuantity; i++) {
            showMessageDialog(
                    null,
                    "ENTER CONTRACT #" + (i + 1) + " DATA");

            HourContractEntity contract = new HourContractEntity();

            int day;
            int month;
            int year;

            do {
                //DIA
                Object[] days = dayList.toArray();
                Object choosedDay = showInputDialog(
                        null,
                        "DIA DO CONTRATO",
                        CONTRACT,
                        QUESTION_MESSAGE,
                        null,
                        days,
                        days[0]
                );

                //MÊS
                Object[] months = monthList.toArray();
                Object choosedMonth = showInputDialog(
                        null,
                        "MÊS DO CONTRATO",
                        CONTRACT,
                        QUESTION_MESSAGE,
                        null,
                        months,
                        months[0]
                );

                //ANO
                Object[] years = yearsList.toArray();
                Object choosedYear = showInputDialog(
                        null,
                        "ANO DO CONTRATO",
                        CONTRACT,
                        QUESTION_MESSAGE,
                        null,
                        years,
                        years[0]
                );

                year = parseInt(choosedYear.toString());
                month = parseInt(choosedMonth.toString());
                day = parseInt(choosedDay.toString());

                Calendar dateContract = getInstance();
                dateContract.set(
                        year,
                        month,
                        day);
                contract.setDate(dateContract.getTime());

                if (!validateDate(day, month, year)) {
                    showMessageDialog(null, "INVALID DATE!");
                }

            } while (!validateDate(day, month, year));

            contract.setValuePerHour(Double.valueOf(showInputDialog(null, "VALUE PER HOUR")));
            contract.setHours(Integer.valueOf(showInputDialog(null, "DURATION (HOURS)")));

            worker.addContract(contract);
        }

        showMessageDialog(null, "ENTER MONTH AND YEAR TO CALCULATE INCOME");
        Object[] months = monthList.toArray();
        Object choosedMonth = showInputDialog(
                null,
                "MONTH",
                CONTRACT,
                QUESTION_MESSAGE,
                null,
                months,
                months[0]
        );
        Object[] years = yearsList.toArray();
        Object choosedYear = showInputDialog(
                null,
                "ANO DO CONTRATO",
                CONTRACT,
                QUESTION_MESSAGE,
                null,
                years,
                years[0]
        );
        double result = worker.income(
                parseInt(choosedYear.toString()),
                1 + parseInt(choosedMonth.toString()));

        showMessageDialog(
                null,
                "NAME: " + worker.getName() +
                        "\nDEPARTMENT: " + worker.getDepartment().getName() +
                        "\nINCOME FOR " + choosedMonth + "/" + choosedYear + ": R$ " + result
        );

    }
}

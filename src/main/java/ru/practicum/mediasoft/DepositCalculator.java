package ru.practicum.mediasoft;

import java.util.Scanner;

public class DepositCalculator {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startCalculator();
    }

    public static void getInputData() {
        System.out.println("Enter deposit amount: ");
        double deposit = scanner.nextDouble();
        System.out.println("Enter deposit period (months): ");
        int period = scanner.nextInt();
        System.out.println("Enter annual interest rate: ");
        double interestRate = scanner.nextDouble();

        double result = calculate(deposit, period, interestRate);
        System.out.printf("Сумма на счете через %d месяцев: %.2f рублей%n", period, result);
    }

    public static void startCalculator() {
        System.out.println("Welcome to deposit calculator!");
        getInputData();
    }

    public static double calculate(double deposit, int period, double interestRate) {
        for (int i = 0; i < period; i++) {
            deposit = deposit * (1 + (interestRate / 100) / 12);
        }
        return deposit;
    }
}

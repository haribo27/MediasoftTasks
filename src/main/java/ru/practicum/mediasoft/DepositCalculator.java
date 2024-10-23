package ru.practicum.mediasoft;

import java.util.Scanner;

public class DepositCalculator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        startCalculator();
    }

    public static void getInputData() {
        System.out.println("Enter deposit amount: ");
        double deposit = getPositiveInput();
        System.out.println("Enter deposit period (months): ");
        int period = getInputPeriod();
        System.out.println("Enter annual interest rate: ");
        double interestRate = getPositiveInput();

        double result = calculate(deposit, period, interestRate);
        System.out.printf("Сумма на счете через %d месяцев: %.2f рублей%n", period, result);
    }

    private static int getInputPeriod() {

        int period = scanner.nextInt();
        while (period < 1 || period > 12 ) {
            System.out.println("Must be in range 1-12: ");
            period = scanner.nextInt();
        }
        return period;
    }

    private static double getPositiveInput() {
        double deposit = scanner.nextDouble();
        while (deposit < 0) {
                System.out.println("Enter positive amount: ");
            deposit = scanner.nextDouble();
        }
        return deposit;
    }

    public static void startCalculator() {
        System.out.println("Welcome to deposit calculator!");
        getInputData();
    }

    public static double calculate(double deposit, int period, double interestRate) {
        double monthlyRate = (interestRate / 100) / 12; // Месячная процентная ставка
        return deposit * Math.pow(1 + monthlyRate, period);
    }
}

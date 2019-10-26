package com.javarush.task.task26.task2613;

import com.javarush.task.task26.task2613.command.CommandExecutor;
import com.javarush.task.task26.task2613.exception.InterruptOperationException;

import java.util.Locale;

public class CashMachine {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        try {
            Operation requiredOperation;

            do {
                requiredOperation = ConsoleHelper.askOperation();
                CommandExecutor.execute(requiredOperation);

            } while (requiredOperation != Operation.EXIT);

        } catch (InterruptOperationException interrupt) {
            ConsoleHelper.writeMessage("Good bye!");
        }
    }
}

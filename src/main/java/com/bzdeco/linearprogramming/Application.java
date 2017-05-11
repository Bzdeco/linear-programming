package com.bzdeco.linearprogramming;

import com.bzdeco.linearprogramming.util.UserInput;

/**
 * Created by bzdeco on 10.05.17.
 */
public class Application {

    private static UserInput userInput = new UserInput();

    public static void main(String[] args) {

        int n = userInput.askForNumberOfVariables();
    }
}

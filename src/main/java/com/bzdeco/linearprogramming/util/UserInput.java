package com.bzdeco.linearprogramming.util;

import com.bzdeco.linearprogramming.math.*;
import com.bzdeco.linearprogramming.math.functions.MaximizedFunction;
import com.bzdeco.linearprogramming.math.functions.MinimizedFunction;
import com.bzdeco.linearprogramming.math.functions.ObjectiveFunction;
import com.bzdeco.linearprogramming.math.limits.*;

import java.util.*;

/**
 * Created by bzdeco on 10.05.17.
 */
public class UserInput {

    private Scanner input;
    private Map<String, Inequality> signsOfConstraints = createMapWithSignsOfConstraints();
    private Map<String, ObjectiveFunction> typesOfFunctions = createMapWithTypesOfFunctions();

    public UserInput() {

        this.input = new Scanner(System.in);
    }

    public int askForNumberOfVariables() {

        System.out.print("Number of decision variables: ");
        int numberOfVariables = input.nextInt();
        flushScanner();

        return numberOfVariables;
    }

    public int askForNumberOfConstraints() {

        System.out.print("Number of constraints: ");
        int numberOfConstraints =  input.nextInt();
        flushScanner();

        return numberOfConstraints;
    }

    public List<String> askForVariablesNames(int numberOfVariables) {

        List<String> result = new ArrayList<>();

        System.out.println("Variables names:");

        for(int i = 1; i <= numberOfVariables; i++) {
            System.out.print("\tVariable " + i + ": ");
            result.add(input.nextLine());
        }

        return result;
    }

    public ObjectiveFunction askForObjectiveFunction(List<String> variablesNames) {

        String functionType;

        Polynomial functionPolynomial;
        ObjectiveFunction function;

        System.out.println("OBJECTIVE FUNCTION");

        System.out.print("\tShould the function be MAXIMIZED (max) or MINIMIZED (min): ");
        functionType = input.next();
        flushScanner();
        function = typesOfFunctions.get(functionType);

        functionPolynomial = askForFunctionPolynomial(variablesNames);
        function.setPolynomial(functionPolynomial);

        return function;
    }

    public List<Constraint> askForConstraints(int numberOfConstraints, List<String> variablesNames) {

        System.out.println("CONSTRAINTS");

        List<Constraint> result = new ArrayList<>();
        for(int i = 0; i < numberOfConstraints; i++)
            result.add(askForConstraint(i + 1, variablesNames));

        return result;
    }

    public Space askForSpace(List<String> variablesNames) {

        System.out.println("VARIABLES BOUNDS");

        List<Bounds> spaceBounds = new ArrayList<>();
        for(String name : variablesNames)
            spaceBounds.add(askForVariableBounds(name));

        return new Space(spaceBounds);
    }

    private Variable askForVariable(String name) {

        double coefficient;
        int exponent;

        System.out.println("\tVariable \"" + name + "\":");

        System.out.print("\t\tCoefficient: ");
        coefficient = input.nextDouble();
        flushScanner();

        System.out.print("\t\tExponent: ");
        exponent = input.nextInt();
        flushScanner();

        return new Variable(name, coefficient, exponent);
    }

    private Constraint askForConstraint(int orderNumber, List<String> variablesNames) {

        List<Variable> variables = new ArrayList<>();
        Inequality constraintBound;
        int numberOfDecisionVariables = variablesNames.size();

        System.out.println("Constraint " + orderNumber + ":");

        for(int i = 0; i < numberOfDecisionVariables; i++)
            variables.add(askForVariable(variablesNames.get(i)));

        constraintBound = askForInequality();

        return new Constraint(
                new Polynomial(variables),
                constraintBound
        );
    }

    private Bounds askForVariableBounds(String variableName) {

        double lowerBound, upperBound;
        System.out.println("Variable " + variableName + " bounds:");

        System.out.print("\tLower bound: ");
        lowerBound = input.nextDouble();
        flushScanner();

        System.out.print("\tUpper bound: ");
        upperBound = input.nextDouble();
        flushScanner();

        return new Bounds(lowerBound, upperBound);
    }

    private Inequality askForInequality() {

        String sign;
        double limit;

        System.out.print("\t\tSign of constraint (<, <=, >, >=): ");
        sign = input.next();
        flushScanner();

        System.out.print("\t\tLimit: ");
        limit = input.nextDouble();
        flushScanner();

        Inequality inequality = signsOfConstraints.get(sign).copy();
        inequality.setLimit(limit);

        return inequality;
    }

    private Polynomial askForFunctionPolynomial(List<String> variablesNames) {

        double coefficient;
        int exponent;

        List<Variable> functionPolynomialVariables = new ArrayList<>();

        System.out.println("\tFunction variables:");
        for(String name : variablesNames) {

            System.out.println("\t\tVariable \"" + name + "\":");

            System.out.print("\t\t\tCoefficient: ");
            coefficient = input.nextDouble();
            flushScanner();

            System.out.print("\t\t\tExponent: ");
            exponent = input.nextInt();
            flushScanner();

            functionPolynomialVariables.add(new Variable(
                    name,
                    coefficient,
                    exponent
            ));
        }

        return new Polynomial(functionPolynomialVariables);
    }

    private Map<String, Inequality> createMapWithSignsOfConstraints() {

        Map<String, Inequality> result = new HashMap<>();
        result.put("<", new Lower());
        result.put("<=", new LowerOrEqual());
        result.put(">", new Greater());
        result.put(">=", new GreaterOrEqual());

        return result;
    }

    private Map<String, ObjectiveFunction> createMapWithTypesOfFunctions() {

        Map<String, ObjectiveFunction> result = new HashMap<>();
        result.put("max", new MaximizedFunction());
        result.put("min", new MinimizedFunction());

        return result;
    }

    private void flushScanner() {
        input.nextLine();
    }
}

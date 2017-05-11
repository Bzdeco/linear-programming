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
        return input.nextInt();
    }

    public int askForNumberOfConstraints() {

        System.out.print("Number of constraints: ");
        return input.nextInt();
    }

    public ObjectiveFunction askForObjectiveFunction(List<Variable> variables) {

        String functionType;

        Polynomial functionPolynomial;
        ObjectiveFunction function;

        System.out.println("Objective function:");

        System.out.print("\tShould the function be MAXIMIZED (max) or MINIMIZED (min): ");
        functionType = input.next();
        function = typesOfFunctions.get(functionType);

        functionPolynomial = askForFunctionPolynomial(variables);
        function.setPolynomial(functionPolynomial);

        return function;
    }

    public Constraint askForConstraint(int orderNumber, int numberOfDecisionVariables) {

        List<Variable> variables = new ArrayList<>();
        Inequality constraintBound;

        System.out.println("Constraint " + orderNumber + ":");

        for(int i = 1; i <= numberOfDecisionVariables; i++)
            variables.add(askForVariable(i));

        constraintBound = askForInequality();

        return new Constraint(
                new Polynomial(variables),
                constraintBound
        );
    }

    public Space askForSpace(List<Variable> variables) {

        List<Bounds> spaceBounds = new ArrayList<>();

        for(Variable variable : variables)
            spaceBounds.add(askForVariableBounds(variable.getName()));

        return new Space(spaceBounds);
    }

    private Variable askForVariable(int orderNumber) {

        String name;
        double coefficient;
        int exponent;

        System.out.println("\tVariable " + orderNumber + ":");

        System.out.print("\t\tName: ");
        name = input.next();

        System.out.print("\n\t\tCoefficient: ");
        coefficient = input.nextDouble();

        System.out.print("\n\t\tExponent: ");
        exponent = input.nextInt();

        return new Variable(name, coefficient, exponent);
    }

    private Bounds askForVariableBounds(String variableName) {

        double lowerBound, upperBound;
        System.out.println("Variable " + variableName + " bounds:");

        System.out.print("\tLower bound: ");
        lowerBound = input.nextDouble();

        System.out.print("\n\tUpper bound: ");
        upperBound = input.nextDouble();

        return new Bounds(lowerBound, upperBound);
    }

    private Inequality askForInequality() {

        String sign;
        double limit;

        System.out.print("\n\t\tSign of constraint (<, <=, >, >=): ");
        sign = input.next();

        System.out.println("\n\t\tLimit: ");
        limit = input.nextDouble();

        Inequality inequality = signsOfConstraints.get(sign);
        inequality.setLimit(limit);

        return inequality;
    }

    private Polynomial askForFunctionPolynomial(List<Variable> variables) {

        double coefficient;
        int exponent;

        List<Variable> functionPolynomialVariables = new ArrayList<>();

        System.out.print("\tFunction variables:");
        for(Variable variable : variables) {

            System.out.print("\n\t\tCoefficient: ");
            coefficient = input.nextDouble();

            System.out.print("\n\t\tExponent: ");
            exponent = input.nextInt();

            functionPolynomialVariables.add(new Variable(
                    variable.getName(),
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
}

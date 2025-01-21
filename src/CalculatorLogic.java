import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorLogic {

    // Main method to calculate based on the selected operation
    public static String calculate(String operation, String[] inputs) throws Exception {
        double result;

        switch (operation) {
            case "Expression Calculator":
                return evaluateExpression(inputs[0]); 
            case "Addition":
                result = performAddition(inputs);
                break;
            case "Subtraction":
                result = performSubtraction(inputs);
                break;
            case "Multiplication":
                result = performMultiplication(inputs);
                break;
            case "Division":
                result = performDivision(inputs);
                break;
            case "Area of Circle":
                result = calculateCircleArea(inputs);
                break;
            case "Area of Rectangle":
                result = calculateRectangleArea(inputs);
                break;
            case "Area of Square":
                result = calculateSquareArea(inputs);
                break;
            case "Area of Triangle":
                result = calculateTriangleArea(inputs);
                break;
            case "Perimeter of Rectangle":
                result = calculateRectanglePerimeter(inputs);
                break;
            case "Perimeter of Square":
                result = calculateSquarePerimeter(inputs);
                break;
            case "Solve Simple Equation (ax+b=0)":
                result = solveSimpleEquation(inputs[0]);
                break;
            default:
                throw new Exception("Unsupported operation");
        }

        return String.format("%.2f", result);
    }

    
    private static String evaluateExpression(String expression) throws ScriptException {
        if (expression == null || expression.trim().isEmpty()) {
            throw new ScriptException("Invalid input: Expression cannot be empty.");
        }

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        expression = expression.replace(" ", ""); 

        Object result = engine.eval(expression);
        return result.toString();
    }

    
    private static double performAddition(String[] inputs) {
        double sum = 0;
        for (String input : inputs) {
            sum += Double.parseDouble(input.trim());
        }
        return sum;
    }

    
    private static double performSubtraction(String[] inputs) {
        double result = Double.parseDouble(inputs[0].trim());
        for (int i = 1; i < inputs.length; i++) {
            result -= Double.parseDouble(inputs[i].trim());
        }
        return result;
    }

    
    private static double performMultiplication(String[] inputs) {
        double result = 1;
        for (String input : inputs) {
            result *= Double.parseDouble(input.trim());
        }
        return result;
    }

    
    private static double performDivision(String[] inputs) throws Exception {
        double result = Double.parseDouble(inputs[0].trim());
        for (int i = 1; i < inputs.length; i++) {
            double divisor = Double.parseDouble(inputs[i].trim());
            if (divisor == 0) {
                throw new Exception("Division by zero is not allowed.");
            }
            result /= divisor;
        }
        return result;
    }

    
    private static double calculateCircleArea(String[] inputs) {
        double radius = Double.parseDouble(inputs[0].trim());
        return Math.PI * radius * radius;
    }

    
    private static double calculateRectangleArea(String[] inputs) {
        double length = Double.parseDouble(inputs[0].trim());
        double width = Double.parseDouble(inputs[1].trim());
        return length * width;
    }

    
    private static double calculateSquareArea(String[] inputs) {
        double side = Double.parseDouble(inputs[0].trim());
        return side * side;
    }

    
    private static double calculateTriangleArea(String[] inputs) {
        double base = Double.parseDouble(inputs[0].trim());
        double height = Double.parseDouble(inputs[1].trim());
        return 0.5 * base * height;
    }

    
    private static double calculateRectanglePerimeter(String[] inputs) {
        double length = Double.parseDouble(inputs[0].trim());
        double width = Double.parseDouble(inputs[1].trim());
        return 2 * (length + width);
    }

    
    private static double calculateSquarePerimeter(String[] inputs) {
        double side = Double.parseDouble(inputs[0].trim());
        return 4 * side;
    }

    
    private static double solveSimpleEquation(String input) {
        String[] parts = input.split("x");
        double a = Double.parseDouble(parts[0].trim());
        double b = Double.parseDouble(parts[1].split("=")[0].trim());
        return -b / a;
    }
}

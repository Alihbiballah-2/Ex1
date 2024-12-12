Ex1 Number Conversion and Calculation
This project provides a Java-based implementation for number conversion and basic operations using a custom number format. It includes a utility class (Ex1.java) with static methods for conversion and validation, and a main program (Ex1Main.java) to demonstrate its usage.

Features
Custom Number Format: Numbers are represented as strings in the format <number>b<base>, where:
<number> is the numeric value.
<base> specifies the numeric base (binary, octal, decimal, hexadecimal, etc.).
Examples:
135bA (Base-10 default)
100111b2 (Base-2 binary)
EFbG (Base-16 hexadecimal)

Implemented Functions in Ex1 Class:
number2Int(String num): Converts a number string to its decimal representation.
isNumber(String a): Validates the number format.
int2Number(int num, int base): Converts a decimal integer to a string representation in the specified base.
equals(String n1, String n2): Compares two number strings for equality.
maxIndex(String[] arr): Finds the index of the largest number in an array.

Interactive Main Program:
Ex1Main allows users to input two number strings, validate and convert them, perform arithmetic operations, and identify the largest result.

Usage
Prerequisites
Java Development Kit (JDK) 8 or higher
A Java IDE or terminal for compilation and execution

Setup
Place the Ex1.java and Ex1Main.java files in the same project directory under the package assignments.ex1.
Compile the files:
javac assignments/ex1/Ex1.java assignments/ex1/Ex1Main.java
Run the main program:
java assignments.ex1.Ex1Main

How to Use
Input: Enter two numbers in the custom format <number>b<base>. Example: 101b2 or FFbG.
Operations:
The program calculates the sum and product of the two numbers.
Determines the maximum among the input numbers, their sum, and their product.
Quit: Type quit to exit the program.

Example Session
Ex1 class solution:
Enter a string as number#1 (or "quit" to end the program): 
101b2
A5b16

Max number over [5,165,170,825] is :3
quiting now...


Notes
Invalid inputs or unsupported formats return default or error values (e.g., -1 for invalid conversions).
The Ex1 utility class performs robust validation to ensure proper number format and base range.

Limitations
The implementation assumes non-empty input for the main program.
Limited to bases 2 through 16.

Future Enhancements
Extend support for larger bases and custom numeral systems.
Add error handling and better user feedback for invalid inputs.

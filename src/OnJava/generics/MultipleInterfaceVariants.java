package OnJava.generics;

public class MultipleInterfaceVariants {
}

interface Payable<T> {}

class Employee1 implements Payable<Employee> {}

/*由于泛型擦除, Payable<Employee>和Payable<Hourly>都会被擦除为Payable，这会导致在Hourly中实现了Payable两次
class Hourly extends Employee1 implements Payable<Hourly> {}
*/


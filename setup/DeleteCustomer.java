import javax.swing.JOptionPane;;

public class DeleteCustomer {
  public static void main(String[] args) {
    int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Customer ID"));
    System.out.println(id);
    JOptionPane.showMessageDialog(null, "Customer Deleted Successfully");
  }
}

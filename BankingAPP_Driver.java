import java.util.Scanner;
class BankingApp
{
    double balance=20000;
    //static double	cur_balance;
    public  void withdraw(double amount)
    {
        if(amount>=0&&amount<=balance)
        {
            if((amount%100==0)&&(amount%500==0))
            {
                balance=balance-amount;
                System.out.println("Successfully withdrawn");
            }
        }
        else if (amount>balance)
            System.out.println("amount exceeded");
        else
            System.out.println("invalid amount");

    }
    public void deposit(double amount)
    {
        if(amount>0)
        {
            if((amount%100==0)&&(amount%500==0))
            {
                balance=balance+amount;
                System.out.println("Successfully deposited");
            }
        }
        else
            System.out.println("invalid deposit");
    }
    public void checkBalance()
    {

        System.out.println("current Balance is"+balance);

    }
}
class BankingApp_Driver{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean	b=true;
        BankingApp a = new BankingApp();
        while(b)
        {
            System.out.println("Enter the option");

            System.out.println(" 1.Withdraw\n 2.Deposit\n 3.checkBalance?\n 4.Exit");
            int choice=sc.nextInt();

            switch(choice)
            {
                case 1:
                {
                    System.out.println("Enter amount to withdraw");
                    double amount=sc.nextDouble();
                    a.withdraw(amount);
                    break;
                }
                case 2:
                {
                    System.out.println("Enter the amount to deposit");
                    double amount=sc.nextDouble();
                    a.deposit(amount);
                    break;
                }
                case 3:
                {
                    a.checkBalance();
                    break;
                }
                case 4:
                {
                    b=false;
                    System.out.println("exit");
                    break;
                }
            }
        }
    }
}

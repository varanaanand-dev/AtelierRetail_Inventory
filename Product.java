
public class Product
{
    String name;
    double price;
    String category;
    String collection;
    int stock;

    public Product(String name, double price, String category, String collection, int stock )
    {
        this.name = name;
        this.price = price;
        this.category= category;
        this.collection = collection;
        this.stock = stock;
    }
    public void sell(int productQuantity)
    {
        if (productQuantity <= stock && productQuantity > 0 )
        {
            stock -= productQuantity;
            System.out.println("Sale Successful!");
            System.out.println("Remaining Stock: "+ stock);
        }
        else {
            System.out.println("Sale Unsuccessful, Please check available stock below");
            System.out.println("Available Stock: "+ stock);

        }
    }

    public void restock(int restockQuantity)
    {
        if (restockQuantity <= 0)
        {
            System.out.println("Unsucessful Restock, Please add above 0 items");
        } else {
            stock += restockQuantity;
            System.out.println("Restock Successful!");
            System.out.println("New Stock Amount: " + stock);
        }
    }


}



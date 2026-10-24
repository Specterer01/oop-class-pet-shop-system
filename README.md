# Pet Shop System

A small Java console program that models a pet shop. You can browse the pets in stock, check which ones can be sold, and buy one as a customer. Buying a pet applies the customer's membership discount and can move them up to a higher tier.

## Files

- `Pet.java` holds the data and behaviour for one pet (ID, name, species, age, price, vaccinated, available).
- `Customer.java` holds the same for one customer (ID, name, phone, membership, points, total spent, active).
- `PetShopMain.java` has the main method, the sample data, and the menu.

All attributes are private and only reachable through getters and setters. The setters check values before accepting them, for example no negative prices.

## How to run

You need a JDK installed (check with `javac -version`).

```
javac *.java
java PetShopMain
```

## How to use it

The program prints the inventory and customer list first, then shows a menu:

1. Buy a pet (type a pet ID like `P001`)
2. View my account
3. Update my phone number
4. Exit

Pets that aren't vaccinated, or are already sold, can't be bought.

## Membership rules

| Tier | Discount | How to reach it |
| --- | --- | --- |
| Standard | 0% | Default |
| Silver | 8% | Spend RM1000 in total |
| Gold | 15% | Spend RM3000 in total |

Customers also earn 1 loyalty point for every RM10 spent.

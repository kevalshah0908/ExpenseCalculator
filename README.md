# ExpenseCalculator
This is a backend application wherein there are 2 endpoints of the API - to add a user, to add multiple expenses.

Sample Requests:

1. Add User:
{
  "FirstName" : "Test",
  "LastName" : "User",
  "ContactNumber" : "91XXXXXXXXXX"
}

2. Add Expenses:
{
  "expenses": [
    {
      "name": "Room rent",
      "amount": 10000,
      "paid_by": "Test1",
      "split_by": "equal"
    },
    {
      "name": "EB Bill",
      "amount": 1000,
      "paid_by": "Test2",
      "split_by": "equal"
    }
  ]
}

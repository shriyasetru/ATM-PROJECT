import tkinter as tk
import tkinter.simpledialog as simpledialog
import tkinter.messagebox as messagebox

class ATM_GUI:
    def __init__(self, master):
        self.master = master
        master.title("ATM Machine")

        self.label_atm_number = tk.Label(master, text="ATM number:")
        self.label_atm_number.grid(row=0, column=0, sticky="w")

        self.entry_atm_number = tk.Entry(master)
        self.entry_atm_number.grid(row=0, column=1)

        self.label_pin = tk.Label(master, text="PIN:")
        self.label_pin.grid(row=1, column=0, sticky="w")

        self.entry_pin = tk.Entry(master, show="*")
        self.entry_pin.grid(row=1, column=1)

        self.button_submit = tk.Button(master, text="Submit", command=self.validate_user)
        self.button_submit.grid(row=2, column=0, columnspan=2)

        self.frame_options = tk.Frame(master)
        self.frame_options.grid(row=3, column=0, columnspan=2)

        self.button_change_pin = tk.Button(self.frame_options, text="Change PIN", command=self.change_pin)
        self.button_change_pin.grid(row=0, column=0)

        self.button_check_balance = tk.Button(self.frame_options, text="Check Balance", command=self.check_balance)
        self.button_check_balance.grid(row=0, column=1)

        self.button_withdraw = tk.Button(self.frame_options, text="Withdraw", command=self.withdraw)
        self.button_withdraw.grid(row=1, column=0)

        self.button_deposit = tk.Button(self.frame_options, text="Deposit", command=self.deposit)
        self.button_deposit.grid(row=1, column=1)

        self.button_mini_statement = tk.Button(self.frame_options, text="Mini Statement", command=self.mini_statement)
        self.button_mini_statement.grid(row=2, column=0, columnspan=2)

        self.button_exit = tk.Button(master, text="Exit", command=master.quit)
        self.button_exit.grid(row=4, column=0, columnspan=2)

    def validate_user(self):
        atm_number = int(self.entry_atm_number.get())
        pin = int(self.entry_pin.get())

        found = False
        for account in accounts:
            if account.getAtmNumber() == atm_number and account.getPin() == pin:
                self.current_account = account
                found = True
                break

        if found:
            self.enable_buttons()
        else:
            messagebox.showerror("Error", "Invalid ATM number or PIN. Please try again.")
            self.entry_atm_number.delete(0, tk.END)
            self.entry_pin.delete(0, tk.END)

    def enable_buttons(self):
        for button in [self.button_change_pin, self.button_check_balance, self.button_withdraw, self.button_deposit, self.button_mini_statement]:
            button.config(state="normal")

    def change_pin(self):
        new_pin = simpledialog.askinteger("Change PIN", "Enter new PIN:")
        if new_pin is not None:
            self.current_account.changePin(new_pin)
            messagebox.showinfo("Success", "PIN changed successfully")

    def check_balance(self):
        messagebox.showinfo("Balance", f"Your available balance is: ${self.current_account.getBalance()}")

    def withdraw(self):
        amount = simpledialog.askfloat("Withdraw", "Enter amount to withdraw:")
        if amount is not None:
            self.current_account.withdraw(amount)
            messagebox.showinfo("Withdraw", f"You have withdrawn: ${amount}")

    def deposit(self):
        amount = simpledialog.askfloat("Deposit", "Enter amount to deposit:")
        if amount is not None:
            self.current_account.deposit(amount)
            messagebox.showinfo("Deposit", f"You have deposited: ${amount}")

    def mini_statement(self):
        messagebox.showinfo("Mini Statement", self.current_account.getMiniStatement())

class Account:
    def __init__(self, atm_number, pin, balance):
        self.atmNumber = atm_number
        self.pin = pin
        self.balance = balance
        self.miniStatement = ""

    def getAtmNumber(self):
        return self.atmNumber

    def getPin(self):
        return self.pin

    def getBalance(self):
        return self.balance

    def setBalance(self, amount):
        self.balance += amount

    def getMiniStatement(self):
        return self.miniStatement

    def setMiniStatement(self, statement):
        self.miniStatement = statement

    def checkBalance(self):
        return self.balance

    def withdraw(self, amount):
        if self.balance >= amount:
            self.balance -= amount
            transaction = f"Withdrawn ${amount}\n"
            self.miniStatement += transaction
        else:
            messagebox.showerror("Error", "Insufficient balance")

    def deposit(self, amount):
        self.balance += amount
        transaction = f"Deposited ${amount}\n"
        self.miniStatement += transaction

    def changePin(self, new_pin):
        self.pin = new_pin

accounts = [Account(12345, 6789, 500.0), Account(67890, 1234, 1000.0)]

root = tk.Tk()
atm_gui = ATM_GUI(root)
root.mainloop()

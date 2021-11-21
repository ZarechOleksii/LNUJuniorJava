
public class Contact {
	public String Name;
	public String Surname;
	public String Address;
	public String PhoneNumber;
	
	/*
	 * public Contact() { this("Unknown", "", "", ""); }
	 * 
	 * public Contact(String name) { this(name, "", "", ""); }
	 */
	
	public void SetName(String name)
	{
		Name = name;
	}
	
	public void SetSurName(String surname)
	{
		Surname = surname;
	}
	
	public void SetAddress(String address)
	{
		Address = address;
	}
	
	public void SetPhoneNumber(String phoneNumber)
	{
		PhoneNumber = phoneNumber;
	}
	
	public Contact(String name, String surname, String address, String phoneNumber)
	{
		Name = name;
		Surname = surname;
		Address = address;
		PhoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return Name + ", " + Surname + ", " + Address + ", " + PhoneNumber;
	}
}

package pl.kasperdubiel.gpswersja2;

public class Person
{
	String name;
	String age;
	int photoid;

	public Person(String name, String age, int photoid)
	{
		this.name = name;
		this.age = age;
		this.photoid = photoid;
	}

	public String getName()
	{
		return name;
	}

	public String getAge()
	{
		return age;
	}

	public int getPhotoid()
	{
		return photoid;
	}
}

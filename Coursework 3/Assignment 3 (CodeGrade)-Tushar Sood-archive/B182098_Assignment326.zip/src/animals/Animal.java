package animals;

/**
 * You can modify the contents of this class, but you cannot:
 * - change the name, parameters or return types of provided methods
 * - remove it entirely
 */
public abstract class Animal
{

	private String nickname;


	public Animal(String nickname) {
		this.setNickname(nickname);
	}


	/**
	 * Check whether two animals can live together.
	 * @param animal The animal for which to check compatibility with this animal.
	 * @return Returns true for compatible animals and false otherwise.
	 */
	public abstract boolean isCompatibleWith(Animal animal);

	/**
	 * @return the nickname of the animal
	 */
	public String getNickname(){   //was abstract, but i added the implementation
		return nickname;
	}

	public void setNickname(String nickname){
		this.nickname = nickname;
	}
}

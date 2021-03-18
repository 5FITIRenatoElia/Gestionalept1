package model;

public class Aziende{
	private int idAzienda;
	private String anagrafica, userId, passwd;
	public Aziende(int idAzienda, String anagrafica, String userId, String passwd) {
		this.idAzienda = idAzienda;
		this.anagrafica = anagrafica;
		this.userId = userId;
		this.passwd = passwd;
	}
	public int getIdAzienda() {
		return idAzienda;
	}
	public String getAnagrafica() {
		return anagrafica;
	}
	public String getUserId() {
		return userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setIdAzienda(int idAzienda) {
		this.idAzienda = idAzienda;
	}
	public void setAnagrafica(String anagrafica) {
		this.anagrafica = anagrafica;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

}

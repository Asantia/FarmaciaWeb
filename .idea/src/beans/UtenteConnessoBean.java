package beans;

/**
 * Created by csantia on 9/12/2017.
 */
public class UtenteConnessoBean implements java.io.Serializable{

        private String email;
        private String password;
        private String abilitazione;
        private int id_farmacia;
        private boolean connesso=false;

        public UtenteConnessoBean() { }

        public String getEmail() {
            return email;
        }
        public void setEmail(String nome) {
            this.email = email;
        }

        public boolean getConnesso() {
            return connesso;
        }
        public void setConnesso(boolean connesso) {
            this.connesso = connesso;
        }

        public String getAbilitazione() {
            return abilitazione;
        }
        public void setAbilitazione(String abilitazione) {
            this.abilitazione = abilitazione;
        }

        public int getIdFarmacia() {
            return id_farmacia;
        }
        public void setIdFarmacia(int id_farmacia) {
            this.id_farmacia = id_farmacia;
        }

}

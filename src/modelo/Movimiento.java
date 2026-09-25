package modelo;

public class Movimiento {
    private int idMovimiento;
    private String nomMovimiento;
    private Integer potencia;
    private String tipo; 
    private String estado;
    private Integer quita;
    private Integer turnos;
    private String mejora;
    private Integer cantMejora;
    private int nivelAprendizaje;

    public Movimiento(int idMovimiento, String nomMovimiento, Integer potencia, String tipo, String estado,
                      Integer quita, Integer turnos, String mejora, Integer cantMejora, int nivelAprendizaje) {
        this.idMovimiento = idMovimiento;
        this.nomMovimiento = nomMovimiento;
        this.potencia = potencia;
        this.tipo = tipo;
        this.estado = estado;
        this.quita = quita;
        this.turnos = turnos;
        this.mejora = mejora;
        this.cantMejora = cantMejora;
        this.nivelAprendizaje = nivelAprendizaje;
    }
    
    // Getters and Setters
    public int getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(int idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public String getNomMovimiento() {
        return nomMovimiento;
    }

    public void setNomMovimiento(String nomMovimiento) {
        this.nomMovimiento = nomMovimiento;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getQuita() {
        return quita;
    }

    public void setQuita(Integer quita) {
        this.quita = quita;
    }

    public Integer getTurnos() {
        return turnos;
    }

    public void setTurnos(Integer turnos) {
        this.turnos = turnos;
    }

    public String getMejora() {
        return mejora;
    }

    public void setMejora(String mejora) {
        this.mejora = mejora;
    }

    public Integer getCantMejora() {
        return cantMejora;
    }

    public void setCantMejora(Integer cantMejora) {
        this.cantMejora = cantMejora;
    }

    public int getNivelAprendizaje() {
        return nivelAprendizaje;
    }

    public void setNivelAprendizaje(int nivelAprendizaje) {
        this.nivelAprendizaje = nivelAprendizaje;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "idMovimiento=" + idMovimiento +
                ", nomMovimiento='" + nomMovimiento + '\'' +
                ", potencia=" + potencia +
                ", tipo='" + tipo + '\'' +
                ", estado='" + estado + '\'' +
                ", quita=" + quita +
                ", turnos=" + turnos +
                ", mejora='" + mejora + '\'' +
                ", cantMejora=" + cantMejora +
                ", nivelAprendizaje=" + nivelAprendizaje +
                '}';
    }
    
    @Override
    public boolean equals(Object object) {
    	if (this == object) return true;
    	if (!(object instanceof Movimiento)) return false;
    	Movimiento movimiento= (Movimiento)object;
    	return (this.idMovimiento  == movimiento.getIdMovimiento());
    }
}

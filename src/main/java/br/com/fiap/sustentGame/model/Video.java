
package br.com.fiap.sustentGame.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_VIDEO")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String urlVideo;
    private String status = "PENDENTE";
    private LocalDateTime dataEnvio = LocalDateTime.now();
    private Integer pontosRecebidos = 0;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", nullable = false)
    @NotNull
    private Usuario usuario;

    public Video(String urlVideo, Usuario usuario) {
        this.urlVideo = urlVideo;
        this.usuario = usuario;
    }

    public Video() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    public Integer getPontosRecebidos() {
        return pontosRecebidos;
    }

    public void setPontosRecebidos(Integer pontosRecebidos) {
        this.pontosRecebidos = pontosRecebidos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

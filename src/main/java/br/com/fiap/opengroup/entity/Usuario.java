package br.com.fiap.opengroup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "OG_TB_USUARIO", uniqueConstraints = {
        @UniqueConstraint(name = "UK_IDENTIFICACAO", columnNames = "IDENTIFICACAO"),
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "EMAIL_USUARIO")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_OG_USUARIO")
    @SequenceGenerator(name = "SQ_OG_USUARIO", sequenceName = "SQ_OG_USUARIO", allocationSize = 1)
    @Column(name = "ID_USUARIO")
    private Long id;

    @Column(name = "EMAIL_USUARIO")
    private String email;

    @Column(name = "DATA_CRIACAO")
    private LocalDate dataCriacao;

    @Column(name = "IDENTIFICACAO")
    private String identificacao;

    @Column(name = "TELEFONE")
    private String telefone;

    @Column(name = "SENHA")
    private String senha;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(
            name = "ID_CLIENTE",
            referencedColumnName = "ID_CLIENTE" ,
            foreignKey = @ForeignKey(
                    name = "FK_USUARIO_CLIENT"
            )
    )
    private Cliente cliente;

}

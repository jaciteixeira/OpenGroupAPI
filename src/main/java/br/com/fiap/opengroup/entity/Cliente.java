package br.com.fiap.opengroup.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "OG_TB_CLIENTE", uniqueConstraints = {
        @UniqueConstraint(name = "UK_CPF", columnNames = "CPF_CLIENTE"),
        @UniqueConstraint(name = "UK_EMAIL", columnNames = "EMAIL_CLIENTE")
})
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CLIENTE")
    @SequenceGenerator(name = "SQ_CLIENTE", sequenceName = "SQ_CLIENTE", allocationSize = 1)
    @Column(name = "ID_CLIENTE")
    private Long id;

    @Column(name = "NM_CLIENTE")
    private String nome;

    @Column(name = "EMAIL_CLIENTE")
    private String email;

    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;

    @Column(name = "GENERO_CLIENTE")
    private String genero;

    @Column(name = "ENDERECO_CLIENTE")
    private String cep;

    @Column(name = "TELEFONE_CLIENTE")
    private String telefone;

    @Column(name = "CPF_CLIENTE")
    private String cpf;

    @Column(name = "PROFISSAO_CLIENTE")
    private String profissao;

    @Column(name = "ESTADO_CIVIL")
    private String estadoCivil;

    @Column(name = "ULTIMA_COMPRA")
    private LocalDate ultimaCompra;

//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "OG_TB_ARQUIVO_CLIENTE",
//            joinColumns = {
//                    @JoinColumn(
//                            name = "CLIENTE",
//                            referencedColumnName = "ID_CLIENTE",
//                            foreignKey = @ForeignKey(
//                                    name = "FK_CLIENTE_ARQUIVO"
//                            )
//                    )
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(
//                            name = "ARQUIVOS",
//                            referencedColumnName = "ID_ARQUIVO",
//                            foreignKey = @ForeignKey(
//                                    name = "FK_ARQUIVO_CLIENTE"
//                            )
//                    )
//            }
//    )
//    private Set<Arquivo> arquivos = new LinkedHashSet<>();

//    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
//    @JoinTable(
//            name = "OG_TB_PREDICAO_CLIENTE",
//            joinColumns = {
//                    @JoinColumn(
//                            name = "CLIENTE",
//                            referencedColumnName = "ID_CLIENTE",
//                            foreignKey = @ForeignKey(
//                                    name = "FK_CLIENTE_PREDICAO"
//                            )
//                    )
//            },
//            inverseJoinColumns = {
//                    @JoinColumn(
//                            name = "PREDICAO",
//                            referencedColumnName = "ID_PREDICAO",
//                            foreignKey = @ForeignKey(
//                                    name = "FK_PREDICAO_CLIENTE"
//                            )
//                    )
//            }
//    )
//    private Set<Predicao> predicoes = new LinkedHashSet<>();

}

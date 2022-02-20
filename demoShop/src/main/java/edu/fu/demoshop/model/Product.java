package edu.fu.demoshop.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(columnDefinition = "varchar(100) not null")
    @NotEmpty
    @Size(max =100 , message = "ma san pham khong qua 100 tu")
    private String code;

    @Column(columnDefinition = "nvarchar(100) not null")
    @NotEmpty
    @Size(max =100 , message = "ten san pham khong qua 100 tu")
    private String name;

    @Column(nullable = false)
    @NotNull
    private int quantity;

    @Column(nullable = false)
    @NotNull
    private double price;

    @Column(columnDefinition = "nvarchar(500) not null")
    private String image;

    @Column(columnDefinition = "nvarchar(500) not null")
    @NotEmpty
    @Size(max =500 , message = "ma san pham khong qua 500 tu")
    private String description;

    @Column
    private boolean ruleDrop = Boolean.TRUE;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="categoryId")
    @JsonBackReference
    private Category category;


    @OneToMany(mappedBy = "product" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OrderDetail>  orderDetails;
}

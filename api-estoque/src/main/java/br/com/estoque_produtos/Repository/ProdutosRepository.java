package br.com.estoque_produtos.Repository;

import br.com.estoque_produtos.Entitys.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    List<Produtos> findProdutosById(Long id);
}

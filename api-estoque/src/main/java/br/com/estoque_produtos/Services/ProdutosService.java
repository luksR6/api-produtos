package br.com.estoque_produtos.Services;

import br.com.estoque_produtos.Entitys.Produtos;
import br.com.estoque_produtos.Exceptions.CodigoBarrasException;
import br.com.estoque_produtos.Exceptions.IdInexistenteException;
import br.com.estoque_produtos.Repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public List<Produtos> listarProdutos(){
        List<Produtos> produtosResult = produtosRepository.findAll();

        return produtosResult;
    }

    public List<Produtos> listarPorId(Long id){
        List<Produtos> produtos = produtosRepository.findProdutosById(id);

        return produtos;
    }

    public Produtos criarProdutos(Produtos produtos) throws CodigoBarrasException {
        if (produtos.getCodigoBarras() == null || produtos.getCodigoBarras().length() != 13){
            throw new CodigoBarrasException("Código de barras inválido, deve ter 13 caracteres");
        }

        return produtosRepository.save(produtos);
    }

    public Produtos atualizarProdutos(Long id, Produtos produtos) throws CodigoBarrasException, IdInexistenteException{
        produtos.setId(id);

        if (!produtosRepository.existsById(id)){
            throw new IdInexistenteException("Id inexistente");
        } else if (produtos.getCodigoBarras() == null || produtos.getCodigoBarras().length() != 13) {
            throw new CodigoBarrasException("Código de barras inválido, deve ter 13 caracteres");
        }

        return produtosRepository.save(produtos);
    }

    public Produtos atualizarEstoque(Long id, double estoque) throws IdInexistenteException {
        Produtos produtoExistente = produtosRepository.findById(id)
                .orElseThrow(() -> new IdInexistenteException("Id inexistente"));

        produtoExistente.setEstoque(estoque);

        return produtosRepository.save(produtoExistente);
    }

    public void deletarProdutos(Long id){
        produtosRepository.deleteById(id);
    }
}

package br.com.estoque_produtos.Controllers;

import br.com.estoque_produtos.Entitys.Produtos;
import br.com.estoque_produtos.Exceptions.CodigoBarrasException;
import br.com.estoque_produtos.Exceptions.IdInexistenteException;
import br.com.estoque_produtos.Repository.ProdutosRepository;
import br.com.estoque_produtos.Services.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {
    
    @Autowired
    private ProdutosService produtosService;

    @Autowired
    private ProdutosRepository produtosRepository;

    @PostMapping("/criar")
    public ResponseEntity<?> criarProdutos(@RequestBody Produtos produtos) throws CodigoBarrasException{
        try{
            return ResponseEntity.ok(produtosService.criarProdutos(produtos));
        } catch (CodigoBarrasException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Produtos>> listarProdutos() {

        return ResponseEntity.ok(produtosService.listarProdutos());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<List<Produtos>> listarProdutosPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtosService.listarPorId(id));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarProdutos(@PathVariable Long id, @RequestBody Produtos produto){
        try {
            Produtos produtoAtualizado = produtosService.atualizarProdutos(id, produto);
            return ResponseEntity.ok(produtoAtualizado);

        } catch (IdInexistenteException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (CodigoBarrasException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/estoque/{id}")
    public ResponseEntity<?> atualizarEstoque(@PathVariable Long id, @RequestBody double estoques){
        try {
            Produtos produtoAtualizado = produtosService.atualizarEstoque(id, estoques);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (IdInexistenteException e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Produtos> deletarProduto(@PathVariable Long id){
        produtosService.deletarProdutos(id);
        return ResponseEntity.ok(null);
    }
}

package entidades.livros;

import aed3.Arquivo;
import aed3.HashExtensivel;

public class ArquivoLivros extends Arquivo<Livro> {

    HashExtensivel<ParNomeID> indiceIndiretoNome;

    public ArquivoLivros(String nomePasta) throws Exception {
        super(nomePasta, Livro.class.getConstructor());
        indiceIndiretoNome = new HashExtensivel<>(
                ParNomeID.class.getConstructor(),
                4,
                nomePasta + "/indiceNome.1.db",
                nomePasta + "/indiceNome.2.db");
    }

    // ---------------------
    // CREATE
    // ---------------------
    @Override
    public int create(Livro livro) throws Exception {
        int id = super.create(livro);
        livro.setID(id);
        indiceIndiretoNome.create(
                new ParNomeID(livro.getTitulo(), id));
        return id;
    }

    // ---------------------
    // READ (email)
    // ---------------------
    public Livro read(String nome) throws Exception {
        ParNomeID p = indiceIndiretoNome.read(Math.abs(nome.hashCode()));
        if (p == null)
            return null;
        return read(p.getId()); // método da superclasse
    }

    public boolean update(Livro novoLivro, String nome) throws Exception {
        // Lembre-se de que, se a chave (nome) for alterada, o update
        // deve excluir o par anterior e inserir o novo par

        ParNomeID pNomeId = new ParNomeID(novoLivro.getTitulo(), novoLivro.getID());
        // deleta aquela posição na hash
        indiceIndiretoNome.delete(Math.abs(nome.hashCode()));
        // insere um novo par
        indiceIndiretoNome.create(pNomeId);
        // ParEmailID p = indiceIndiretoEmail.read(pEmailId.getID());

        return super.update(novoLivro);

    }

    public boolean delete(Livro livro) throws Exception {

        // A chave do índice indireto não é o ID, mas o hashCode do nome.

        indiceIndiretoNome.delete(Math.abs(livro.getTitulo().hashCode()));

        return super.delete(livro.getID());
    }

}

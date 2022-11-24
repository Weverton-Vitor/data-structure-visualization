package com.project.datastructure.queue;


public class FilaEnc {
	private No inicio;  // aponta para o inicio da fila 
	private No fim;    	// aponta para o fim da fila  
	private int nElementos;
	
	/** Cria uma Fila  */
	public FilaEnc() {
		inicio = null;
		fim = null;
		nElementos = 0;
	}

	/**Verifica se a Fila está vazia */
	public boolean vazia () {
		if (nElementos == 0)
			return true;
		else
			return false;
	}

	/** Obtém o tamanho da Fila */
	public int tamanho () {
		return nElementos;
	}

	/** Consulta o elemento do início da fila
	    Retorna -1 se a fila estiver vazia */
	public int primeiro () {
		if (vazia())
			return -1; // Erro: Fila vazia 

		return inicio.getConteudo();
	}

	/** Insere um elemento no fim de uma fila
	    Retorna false se a mem. for insuficiente, true caso contrário*/
	public boolean insere (int valor) {
		No novoNo = new No();
		novoNo.setConteudo(valor);
		novoNo.setProx(null);

	    if (vazia()){    /*Inserção em fila vazia */
	        inicio = novoNo;
	    }
	    else {
			fim.setProx(novoNo); /* liga com a fila */
		}
		fim = novoNo; // atualiza o novo fim 
	    nElementos++;
	    return true;
	}

	/**Retira o elemento do início da fila e retorna o seu valor
	    Retorna -1 se a fila estiver vazia. */
	public int remove() {
		if (vazia()) {
	        return -1; // Erro: Fila vazia 
	    }

		No aux = inicio;
		int valor = inicio.getConteudo();
				
		//if (inicio == fim){ // Fila com 1 elemento 
		if (tamanho() == 1){ // Fila com 1 elemento 
			fim = null;
		}	

		inicio = aux.getProx();
		
		// sugere ao garbage collector que libere a memoria
	    //  da regiao apontada por p
	    aux = null;

	    nElementos--;
		return valor;
	}
}

package br.unb.cic.mhs

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import br.unb.cic.mhs.ast._
import br.unb.cic.mhs.ast.Referencia
import br.unb.cic.mhs.ast.Referencia
import br.unb.cic.mhs.memoria.AmbienteDecFuncao

class ExpressaoAplicacaoTeste extends FlatSpec with Matchers {
  
  "uma aplicacao inc 4 (onde existe inc x = x + 1) " should " levar ao valor 5" in {
    val inc = new DecFuncao("inc", List("x"), List(TInteiro),new ExpressaoSoma(new Referencia("x"), new ValorInteiro(1)), TInteiro)
    val app = new Aplicacao("inc", new ValorInteiro(4))
    
    AmbienteDecFuncao.associar("inc", inc)
    
    app.avaliar().asInstanceOf[ValorInteiro].valor should be (5)
  }
  
  //TODO: isso eh uma aberracao, pois tem a semantica de scopo dinamico. 
  "supondo (def f y = x + y), e avaliamos let x = 10 in f 5 " should " levar ao 15" in {
    val refX = new Referencia("x") 
    val f    = new DecFuncao("f", List("y"), List(TInteiro) ,new ExpressaoSoma(refX, new Referencia("y")), TInteiro)
    val let  = new ExpressaoLet("x", new ValorInteiro(10), new Aplicacao("f", new ValorInteiro(5)))    
 
    AmbienteDecFuncao.associar("f", f)
    
    let.avaliar().asInstanceOf[ValorInteiro].valor should be (15)
  }

  "uma aplicacao inc 4,6 (onde existe inc x = x + y) " should " levar ao valor 10" in {
    val inc = new DecFuncao("inc", List("x","y"), List(TInteiro, TInteiro) ,new ExpressaoSoma(new Referencia("x"), new Referencia("y")), TInteiro)
    val app = new Aplicacao("inc", new ValorInteiro(4), new ValorInteiro(6))

    AmbienteDecFuncao.associar("inc", inc)

    app.avaliar().asInstanceOf[ValorInteiro].valor should be (10)
  }

  "uma aplicacao inc 4,6 (onde existe inc x = x + y, y = y + y ) " should " levar ao valor 10" in {
    val inc = new DecFuncao("inc", List("x","y"), List(TInteiro, TInteiro), new ExpressaoSoma(new Referencia("x"), new Referencia("y")), TInteiro)
    val app = new Aplicacao("inc", new ValorInteiro(4), new ValorInteiro(6))

    AmbienteDecFuncao.associar("inc", inc)

    app.avaliar().asInstanceOf[ValorInteiro].valor should be (10)
  }

}
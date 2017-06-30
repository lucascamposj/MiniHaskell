package br.unb.cic.mhs

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.Referencia
import br.unb.cic.mhs.ast.ExpressaoLet
import br.unb.cic.mhs.ast.Referencia
import br.unb.cic.mhs.ast.ExpressaoLet

/*
 * Classe de teste para expressoes do tipo Let
 */
class ExpressaoLetTeste extends FlatSpec with Matchers {
  
  //esse teste deve estar funcionando.
  "uma expressao let x = 3 in x + 1" should "levar ao valor 4" in {
    //val let = MHSParser.parse("let x = 3 in x + 1")
    val let = new ExpressaoLet("x",new ValorInteiro(3), 
        new ExpressaoSoma(new Referencia("x"), 
            new ValorInteiro(1)))
    
    let.avaliar().asInstanceOf[ValorInteiro].valor should be (4)
    
  }
  
  //esse teste deve estar funcionando. 
  "uma expressao let x = 3 in y = 5 in x + y" should "levar ao valor 8" in{
    val val3 = new ValorInteiro(3)
    val val5 = new ValorInteiro(5)
    val refY = new Referencia("y")
    val refX = new Referencia("x")
    val let1 = new ExpressaoLet("y", val5, new ExpressaoSoma(refX,refY))
    val let2 = new ExpressaoLet("x", val3, let1)
    
    let2.avaliar().asInstanceOf[ValorInteiro].valor should be (8)
  }
  
  //esse caso de teste esta quebrado. Tarefa: fazer esse teste passar.
  "uma expressao let x = 3 in (let x = 5 in x + 1) + x" should "levar ao valor 9" in {
     val val1 = new ValorInteiro(1) 
     val val3 = new ValorInteiro(3)
     val val5 = new ValorInteiro(5) 
     val refX = new Referencia("x") 
     val let1 = new ExpressaoLet("x", val5, new ExpressaoSoma(refX, val1))
     val let2 = new ExpressaoLet("x", val3, new ExpressaoSoma(let1, refX))
     
     let2.avaliar().asInstanceOf[ValorInteiro].valor should be (9)
  }
  
}
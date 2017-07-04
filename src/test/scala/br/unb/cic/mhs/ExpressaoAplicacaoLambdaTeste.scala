package br.unb.cic.mhs

import br.unb.cic.mhs.ast._
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 04/07/17.
  */
class ExpressaoAplicacaoLambdaTeste extends FlatSpec with Matchers {
    "uma aplicacao lambda(x -> x+1)2 " should " levar ao valor 3" in {
        val expLambda = new ExpressaoLambda("x", new ExpressaoSoma(new ReferenciaLambda("x"), new ValorInteiro(1)))
        val appLambda = new AplicacaoLambda(expLambda, new ValorInteiro(2))

        appLambda.avaliar().asInstanceOf[ValorInteiro].valor should be (3)
    }
}

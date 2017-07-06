package br.unb.cic.mhs

import br.unb.cic.mhs.ast._
import br.unb.cic.mhs.memoria.AmbienteDecFuncao
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 04/07/17.
  */
class ExpressaoAplicacaoLambdaTeste extends FlatSpec with Matchers {

    "uma aplicacao lambda(x -> x+1)2 " should " levar ao valor 3" in {
        val expLambda = new DecLambda("x", TInteiro,new ExpressaoSoma(new ReferenciaLambda("x"), new ValorInteiro(1)),TInteiro)
        val appLambda = new AplicacaoLambda(expLambda, new ValorInteiro(2))

        appLambda.avaliar().asInstanceOf[ValorInteiro].valor should be (3)
    }

    "uma aplicacao com arg x com expressoes lambda(x -> x+4) e (x -> x+4) como corpo" should " levar ao valor 3" in {
        val expLambda1 = new DecLambda("x", TInteiro,new ExpressaoSoma(new ReferenciaLambda("x"), new ValorInteiro(4)),TInteiro)
        val expLambda2 = new DecLambda("x", TInteiro,new ExpressaoSoma(new ReferenciaLambda("x"), new ValorInteiro(5)),TInteiro)

        val funcao = new DecFuncao("foo", List("x"), List(TInteiro), new ExpressaoITE(new ValorBooleano(false), expLambda1, expLambda2), TInteiro)
        AmbienteDecFuncao.associar("foo", funcao)
        val expLambda = new Aplicacao("foo", new ValorInteiro(8)).avaliar()


        val appLambda = new AplicacaoLambda(expLambda, new ValorInteiro(7))

        appLambda.avaliar().asInstanceOf[ValorInteiro].valor should be (12)
    }

}


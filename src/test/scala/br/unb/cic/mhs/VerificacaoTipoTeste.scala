package br.unb.cic.mhs

import br.unb.cic.mhs.ast._
import br.unb.cic.mhs.memoria.AmbienteDecFuncao
import br.unb.cic.mhs.visitors.VerificaTipo
import org.scalatest.{FlatSpec, Matchers}

/** Testes com verificação de tipo
  * Created by lucas-mac on 05/07/17.
  */
class VerificacaoTipoTeste extends FlatSpec with Matchers {

    private val VT = new VerificaTipo

    /*
        TESTES EM APLICACAO LAMBDA
     */

    "Teste de tipo de ExpressaoLambda - x -> x + 1" should "TInteiro" in {
        val expLambda = new DecLambda("x", TInteiro,new ExpressaoSoma(new ReferenciaLambda("x"), new ValorInteiro(1)),TInteiro)
        val appLambda = new AplicacaoLambda(expLambda, new ValorInteiro(2))
        appLambda.aceitar(VT) should be (TInteiro)
    }

    "Teste de tipo de ExpressaoLambda - x -> x + 1" should "TErro" in {
        val expLambda = new DecLambda("x", TInteiro,new ExpressaoSoma(new ReferenciaLambda("x"), new ValorBooleano(true)),TInteiro)
        val appLambda = new AplicacaoLambda(expLambda, new ValorInteiro(2))
        appLambda.aceitar(VT) should be (TErro)
    }

    /*
        TESTES EM APLICACAO
     */

    "Teste de tipo da aplicacao" should "levar ao valor TErro" in {
        val inc = new DecFuncao("inc", List("x","y"), List(TInteiro,TInteiro),new ExpressaoSoma(new Referencia("x"), new Referencia("y")), TInteiro)
        val app = new Aplicacao("inc", new ValorBooleano(false), new ValorInteiro(3))

        AmbienteDecFuncao.associar("inc", inc)

        app.aceitar(VT) should be (TErro)
    }

    "Teste de tipo da aplicacao" should "levar ao valor TInteiro" in {
        val inc = new DecFuncao("inc", List("x","y"), List(TInteiro,TInteiro),new ExpressaoSoma(new Referencia("x"), new Referencia("y")), TInteiro)
        val app = new Aplicacao("inc", new ValorInteiro(5), new ValorInteiro(3))

        AmbienteDecFuncao.associar("inc", inc)

        app.aceitar(VT) should be (TInteiro)
    }

    "Teste em argumentos de aplicacao" should "levar ao valor TErro" in {
        val inc = new DecFuncao("inc", List("x","y"), List(TInteiro,TBooleano),new ExpressaoSoma(new Referencia("x"), new Referencia("y")), TInteiro)
        val app = new Aplicacao("inc", new ValorInteiro(5), new ValorInteiro(3))

        AmbienteDecFuncao.associar("inc", inc)

        app.aceitar(VT) should be (TErro)
    }

    /*
        TESTES EM DIFERENCA
     */

    "Teste em diferenca correto" should "levar ao valor TBooleano" in {
        val dif = new ExpressaoDiferenca(new ValorInteiro(4), new ValorInteiro(4));
        dif.aceitar(VT) should be (TBooleano);
    }

    "Teste em diferenca incorreto" should "levar ao valor TInteiro" in {
        val dif = new ExpressaoDiferenca(new ValorBooleano(true), new ValorInteiro(4));
        dif.aceitar(VT) should be (TErro);
    }

    /*
        TESTES EM DIVISAO
     */

    "Teste em divisao correto" should "levar ao valor TInteiro" in {
        val divisao = new ExpressaoDivisao(new ValorInteiro(10), new ValorInteiro(2));
        divisao.aceitar(VT) should be (TInteiro);
    }

    "Teste em divisao incorreto" should "levar ao valor TErro" in {
        val divisao = new ExpressaoDivisao(new ValorBooleano(true), new ValorInteiro(2));
        divisao.aceitar(VT) should be (TErro);
    }

    /*
        TESTES EM OPERACAO E
     */

    "Teste em operacao E correta" should "levar ao valor TBooleano" in {
        val e = new ExpressaoE(new ValorBooleano(false), new ValorBooleano(false));
        e.aceitar(VT) should be (TBooleano);
    }

    "Teste em operacao E incorreta" should "levar ao valor TErro" in {
        val e = new ExpressaoE(new ValorInteiro(1), new ValorBooleano(false));
        e.aceitar(VT) should be (TErro);
    }

    /*
        TESTES EM FATORIAL
     */

    "Teste em fatorial correto" should "levar ao valor TInteiro" in {
        val fat = new ExpressaoFatorial(new ValorInteiro(6));
        fat.aceitar(VT) should be (TInteiro);
    }

    "Teste em fatorial incorreto" should "levar ao valor TErro" in {
        val fat = new ExpressaoFatorial(new ValorBooleano(true));
        fat.aceitar(VT) should be (TErro);
    }

    /*
        TESTES EM IGUALDADE
     */

    "Teste em igualdade correto" should "levar ao valor TBooleano" in {
        val igual = new ExpressaoIgualdade(new  ValorInteiro(1), new ValorInteiro(1));
        igual.aceitar(VT) should be (TBooleano);
    }

    "Teste em igualdade incorreto" should "levar ao valor TErro" in {
        val igual = new ExpressaoIgualdade(new  ValorBooleano(true), new ValorInteiro(1));
        igual.aceitar(VT) should be (TErro);
    }

    /*
        TESTES EM LET
     */

    "Teste em let x = 3 in y = 5 in x + y  correto" should "levar ao valor TInteiro" in{
        val val3 = new ValorInteiro(3)
        val val5 = new ValorInteiro(5)
        val refY = new Referencia("y")
        val refX = new Referencia("x")
        val let1 = new ExpressaoLet("y", val5, new ExpressaoSoma(refX,refY))
        val let2 = new ExpressaoLet("x", val3, let1)

        let2.aceitar(VT) should be (TInteiro)
    }

    "Teste em let x = 3 in x + 3  incorreto" should "levar ao valor TErro" in{
        val val3 = new ValorBooleano(true)
        val refX = new Referencia("x")
        val let1 = new ExpressaoLet("x", val3, new ExpressaoSoma(refX,new ValorInteiro(3)))

        let1.aceitar(VT) should be (TErro)
    }

    /*
        TESTES EM MAIORQUE
     */

    "Teste em Maior Que correto" should "levar ao valor TBooleano" in {
        val mq = new ExpressaoMaiorQue(new ValorInteiro(4), new ValorInteiro(3));
        mq.aceitar(VT) should be(TBooleano);
    }

    "Teste em Maior Que incorreto" should "levar ao valor TErro" in {
        val mq = new ExpressaoMaiorQue(new ValorBooleano(true), new ValorInteiro(3));
        mq.aceitar(VT) should be(TErro);
    }

    /*
        TESTES MENORQUE
     */

    "Teste em Menor Que correto" should "levar ao valor TBooleano" in {
        val mq = new ExpressaoMenorQue(new ValorInteiro(4), new ValorInteiro(3));
        mq.aceitar(VT) should be(TBooleano);
    }

    "Teste em Menor Que incorreto" should "levar ao valor TErro" in {
        val mq = new ExpressaoMenorQue(new ValorBooleano(true), new ValorInteiro(3));
        mq.aceitar(VT) should be(TErro);
    }

    /*
        TESTES MULTIPLICAO
     */

    "Teste em Multiplicacao" should "levar ao valor TInteiro" in {
        val soma = new ExpressaoMultiplica(new ValorInteiro(10), new ValorInteiro(2));
        soma.aceitar(VT) should be (TInteiro);
    }

    "Teste em Multiplicacao" should "levar ao valor TErro" in {
        val soma = new ExpressaoMultiplica(new ValorBooleano(true), new ValorInteiro(2));
        soma.aceitar(VT) should be (TErro);
    }

    /*
        TESTES NEGATIVA
     */

    "Teste em Negativa correto" should "levar ao valor TBooleano" in {
        val negativa = new ExpressaoNegativa(new ValorBooleano(false));
        negativa.aceitar(VT) should be (TBooleano);
    }

    "Teste em Negativa incorreto" should "levar ao valor TErro" in {
        val negativa = new ExpressaoNegativa(new ValorInteiro(3));
        negativa.aceitar(VT) should be (TErro);
    }

    /*
        TESTES OU
     */

    "Teste em operacao OU correto" should "levar ao valor TBooleano" in {
        val ou = new ExpressaoOU(new ValorBooleano(false), new ValorBooleano(false));
        ou.aceitar(VT) should be (TBooleano);
    }

    "Teste em operacao OU incorreto" should "levar ao valor TErro" in {
        val ou = new ExpressaoOU(new ValorInteiro(3), new ValorBooleano(false));
        ou.aceitar(VT) should be (TErro);
    }

    /*
        TESTE Potencia
     */

    "Teste em potenciacao correto" should "levar ao valor TInteiro" in {
        val pot = new ExpressaoPotencia(new ValorInteiro(3), new ValorInteiro(4));
        pot.aceitar(VT) should be (TInteiro);
    }

    "Teste em potenciacao incorreto" should "levar ao valor TErro" in {
        val pot = new ExpressaoPotencia(new ValorBooleano(true), new ValorInteiro(4));
        pot.aceitar(VT) should be (TErro);
    }

    /*
        TESTE SOMA
     */

    "Teste em soma correto" should "levar ao valor TInteiro" in {
        val soma = new ExpressaoSoma(new ValorInteiro(3), new ValorInteiro(4));
        soma.aceitar(VT) should be (TInteiro);
    }

    "Teste em soma incorreto" should "levar ao valor TErro" in {
        val soma = new ExpressaoSoma(new ValorBooleano(true), new ValorInteiro(4));
        soma.aceitar(VT) should be (TErro);
    }

    /*
        TESTE SUBTRACAO
     */

    "Teste em subtracao correto" should "levar ao valor TInteiro" in {
        val sub = new ExpressaoSubtracao(new ValorInteiro(3), new ValorInteiro(4));
        sub.aceitar(VT) should be (TInteiro);
    }

    "Teste em subtracao correto" should "levar ao valor TErro" in {
        val sub = new ExpressaoSubtracao(new ValorBooleano(true), new ValorInteiro(4));
        sub.aceitar(VT) should be (TErro);
    }



}

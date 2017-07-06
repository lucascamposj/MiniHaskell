package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoIgualdade, ValorBooleano, ValorInteiro}
import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by lucas-mac on 05/07/17.
  */
class ExpressaoIgualdadeTeste extends FlatSpec with Matchers {
    "Uma operacao de Igualdade com 2 e 2" should "levar ao valor true" in {
        val soma = new ExpressaoIgualdade(new ValorInteiro(2), new ValorInteiro(2));
        soma.avaliar().asInstanceOf[ValorBooleano].valor should be (true);
    }

    "Uma operacao de Igualdade com 2 e 1" should "levar ao valor false" in {
        val soma = new ExpressaoIgualdade(new  ValorInteiro(2), new ValorInteiro(1));
        soma.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }
}
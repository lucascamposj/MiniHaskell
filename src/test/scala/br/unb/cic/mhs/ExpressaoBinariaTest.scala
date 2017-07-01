package br.unb.cic.mhs

import br.unb.cic.mhs.ast.{ExpressaoBinaria, ValorBooleano}
import org.scalatest.{FlatSpec, Matchers}


/**
  * Created by lucas-mac on 30/06/17.
  */
class ExpressaoBinariaTest extends FlatSpec with Matchers{
    "Uma AND com valores false and true" should "levar ao valor false" in {
        val soma = new ExpressaoBinaria(new ValorBooleano(false), new ValorBooleano(true));
        soma.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }

    "Uma AND com valores false and false" should "levar ao valor false" in {
        val soma = new ExpressaoBinaria(new ValorBooleano(false), new ValorBooleano(false));
        soma.avaliar().asInstanceOf[ValorBooleano].valor should be (false);
    }

    "Uma AND com valores true and true" should "levar ao valor true" in {
        val soma = new ExpressaoBinaria(new ValorBooleano(true), new ValorBooleano(true));
        soma.avaliar().asInstanceOf[ValorBooleano].valor should be (true);
    }

}


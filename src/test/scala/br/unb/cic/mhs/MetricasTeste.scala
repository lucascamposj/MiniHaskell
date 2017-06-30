package br.unb.cic.mhs

import org.scalatest.FlatSpec
import org.scalatest.Matchers
import br.unb.cic.mhs.ast.ExpressaoSoma
import br.unb.cic.mhs.ast.ValorInteiro
import br.unb.cic.mhs.visitors.Metricas

class MetricasTeste extends FlatSpec with Matchers {
  "o total de expressoes 3 + (4 + 5)" should "levar ao valor cinco" in {
    val soma1 = new ExpressaoSoma(new ValorInteiro(4), new ValorInteiro(5))
    val soma2 = new ExpressaoSoma(new ValorInteiro(3), soma1);
    
    val m : Metricas = new Metricas()
    soma2.aceitar(m) should be (5)
  }
}
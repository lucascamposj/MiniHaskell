package br.unb.cic.mhs.ast
import br.unb.cic.mhs.visitors.MHSVisitor

class ExpressaoBinaria(val lhs : Expressao, val rhs : Expressao) extends Expressao{
    override def avaliar(): Valor = {
        val v1 = lhs.avaliar().asInstanceOf[ValorBooleano]
        val v2 = rhs.avaliar().asInstanceOf[ValorBooleano]

        return new ValorBooleano(v1.valor && v2.valor)
    }
    override def verificarTipo(): Tipo = {
        val t1 = lhs.verificarTipo()
        val t2 = rhs.verificarTipo()

        return if(t1.equals(TBooleano) && t2.equals(TBooleano)) TBooleano else TErro
    }
    override def aceitar[T](visitor: MHSVisitor[T]): T = visitor.visitar(this)

}
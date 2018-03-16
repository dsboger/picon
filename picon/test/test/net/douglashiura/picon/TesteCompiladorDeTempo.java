package test.net.douglashiura.picon;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import net.douglashiura.picon.CompiladorDeTempo;

public class TesteCompiladorDeTempo {
	private SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd hh:mm");

	// bug of plus in month, when the day is 31th and is used +

	@Test
	public void tempoComum() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		assertEquals("2010/10/25 02:15", formato.format(operacao.compile("2010/10/25 02:15")));
	}

	@Test
	public void tempoNow() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		assertEquals(formato.format(new Date()), formato.format(operacao.compile("now")));
	}

	@Test
	public void noMesAtualDoAno() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		String atual = "2010/" + dateFormat.format(new Date()) + "/25 02:03";
		assertEquals(atual, formato.format(operacao.compile("2010/-0/25 02:03")));
	}

	@Test
	public void umMesAntesNoAno() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + -1);
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("2008/-1/25 02:03")));
	}

	@Test
	public void umMesDepoisNoAno() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + +1);
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("2008/+1/25 02:03")));
	}

	@Test
	public void quinzeMesesAntesNoAno() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + -15);
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("2008/-15/25 02:03")));
	}

	@Test
	public void umAnoAntes() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + -1);
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("-1/12/25 02:03")));
	}

	@Test
	public void umAnoDepois() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.DATE, 25);
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + +1);
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("+1/12/25 02:03")));
	}

	@Test
	public void doisMesAntesSemHoras() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + -2);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("-0/-2/-0")));
	}

	@Test
	public void umDiaDepois() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + +1);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("2008/12/+1 02:03")));
	}

	@Test
	public void umDiaAntes() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + -1);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("2008/12/-1 02:03")));
	}

	@Test
	public void umDiaAntesDeAgora() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 02);
		calendar.set(Calendar.MINUTE, 03);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + -1);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("-0/-0/-1 02:03")));
	}

	@Test
	public void semHorasUmDiaAntes() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 12);
		calendar.set(Calendar.YEAR, 2008);
		calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + -1);
		assertEquals(formato.format(calendar.getTime()), formato.format(operacao.compile("2008/12/-1")));
	}

	@Test
	public void dataFixaSemHoras() throws ParseException {
		CompiladorDeTempo operacao = new CompiladorDeTempo();
		assertEquals("2008/12/05 12:00", formato.format(operacao.compile("2008/12/05")));
	}

}

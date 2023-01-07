package net.focik.hr.utils.prints;

import org.javamoney.moneta.Money;
import org.javamoney.moneta.format.AmountFormatParams;

import javax.money.format.AmountFormatQueryBuilder;
import javax.money.format.MonetaryAmountFormat;
import javax.money.format.MonetaryFormats;
import java.util.Locale;

public class MoneyUtils {

    private MoneyUtils() { }

    public static String mapMoneyToString(Money money) {
        MonetaryAmountFormat amountFormat = MonetaryFormats.getAmountFormat(
                AmountFormatQueryBuilder.of(new Locale("pl", "PL"))
//                .set(AmountFormatParams.PATTERN, "###,###.## ¤")
                        .set(AmountFormatParams.PATTERN, "###,##0.00 zł")
                        .build());

        return amountFormat.format(money);
    }

}

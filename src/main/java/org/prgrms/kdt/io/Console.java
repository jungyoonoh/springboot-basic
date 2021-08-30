package org.prgrms.kdt.io;

import java.util.Optional;
import java.util.Scanner;
import org.prgrms.kdt.command.CommandType;
import org.prgrms.kdt.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by yhh1056
 * Date: 2021/08/17 Time: 11:32 오후
 */

public class Console implements Output, Input {
    private static final Logger logger = LoggerFactory.getLogger(Console.class);

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void guide() {
        var list = FileUtil.readText(new ClassPathResource("command_guide"));
        list.forEach(this::printLine);
    }

    @Override
    public Optional<CommandType> inputCommand() {
        try {
            return Optional.of(CommandType.valueOf(scanner.nextLine().toUpperCase()));
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public String[] inputVoucher() {
        printVoucherChoice();

        while (true) {
            try {
                String inputVoucher = scanner.nextLine();
                validateSeparator(inputVoucher);
                var split = inputVoucher.split(",");
                validateNumeric(split[1].trim());
                return split;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                logger.error(e.getMessage());
            }
        }
    }

    private void validateSeparator(String inputVoucher) {
        if (!inputVoucher.contains(",")) {
            throw new IllegalArgumentException("쉼표로 구분해서 입력해주세요.");
        }
    }

    private long validateNumeric(String numberString) {
        long number;
        try {
            number = Long.parseLong(numberString);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식으로 입력해주세요.");
        }
        return number;
    }

    @Override
    public void printVoucherChoice() {
        System.out.println("생성하고 싶은 바우처를 선택하고 할인양 또는 할인율을 입력해주세요. (쉼표 ',' 를 사용하여 구분해주세요)");
        System.out.println("1. Fixed Amount Voucher");
        System.out.println("2. PercentDiscount Voucher");
        System.out.println("ex) 입력 예시 1, 1000 또는 2, 20");
    }

    @Override
    public void successCreate() {
        System.out.println("성공적으로 등록되었습니다.");
        printNextCommand();
    }

    @Override
    public void printNextCommand() {
        System.out.println("다음 명령을 입력해주세요.");
    }

    @Override
    public void printExit() {
        System.out.println("프로그램을 종료합니다.");
    }

    @Override
    public void printLine(String line) {
        System.out.println(line);
    }

    @Override
    public void commandError() {
        System.out.println("지원하지 않는 명령어입니다. 다시 입력해주세요.");
    }
}

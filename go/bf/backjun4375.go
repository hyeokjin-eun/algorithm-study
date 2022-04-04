package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	var input =
		"3\n" +
		"7\n" +
		"9901\n" +
		"9999"

	r := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	var answer []int
	for i := 0; ;i++ {
		r.Scan()
		s := r.Text()
		if len(s) < 1 {
			break
		}

		temp := 1
		num,_ := strconv.Atoi(s)
		count := 1
		for temp % num != 0 {
			temp = (temp * 10 + 1) % num
			count++
		}

		answer = append(answer, count)
	}

	for i := 0; i < len(answer); i++ {
		_, _ = w.WriteString(strconv.Itoa(answer[i]))
		if i != len(answer) - 1 {
			_, _ = w.WriteString("\n")
		}
	}

	_ = w.Flush()
}

package main

import (
	"bufio"
	"math"
	"os"
	"strconv"
	"strings"
)

func main() {
	var input =
		"10\n" +
		"20\n" +
		"30\n" +
		"40\n" +
		"50\n" +
		"60\n" +
		"70\n" +
		"80\n" +
		"90\n" +
		"100"

	s := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	var mushroom [10]int
	for i := 0; i < 10; i++ {
		s.Scan()
		mushroom[i], _ = strconv.Atoi(s.Text())
	}

	min := 10000
	answer := 0
	for i := 0; i < 10; i++ {
		sum := 0
		for j := 0; j <= i; j++ {
			sum += mushroom[j]
		}

		if math.Abs(float64(100 - sum)) <= float64(min) {
			min = int(math.Abs(float64(100 - sum)))
			answer = sum
		}
	}


	_, _ = w.WriteString(strconv.Itoa(answer))
	_ = w.Flush()
}

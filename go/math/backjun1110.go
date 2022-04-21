package main

import (
	"bufio"
	"os"
	"strconv"
	"strings"
)

func main() {
	input :=
		"71"

	r := bufio.NewScanner(strings.NewReader(input))
	w := bufio.NewWriter(os.Stdout)

	r.Scan()
	num := r.Text()
	if len(num) == 1 {
		num = "0" + num
	}

	temp := num
	cnt := 0
	for cnt == 0 || temp != num {
		nums := strings.Split(temp, "")
		sum := 0
		for i := 0; i < len(nums); i++ {
			t, _ := strconv.Atoi(nums[i])
			sum += t
		}

		s := strconv.Itoa(sum)
		strs := strings.Split(s, "")
		temp = nums[len(nums)-1] + strs[len(strs)-1]
		cnt++
	}

	_, _ = w.WriteString(strconv.Itoa(cnt))
	_ = w.Flush()
}

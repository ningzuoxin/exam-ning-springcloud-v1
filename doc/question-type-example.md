## SINGLE_CHOICE - 单选题

```json
{
  "correctAnswer": [
    "A"
  ],
  "options": [
    {
      "id": "A",
      "text": "1"
    },
    {
      "id": "B",
      "text": "2"
    },
    {
      "id": "C",
      "text": "4"
    },
    {
      "id": "D",
      "text": "6"
    }
  ],
  "id": 1,
  "type": "SINGLE_CHOICE",
  "content": "以下哪个数字是单数？",
  "explanation": "1 3 5 7 9 是单数"
}
```

## MULTIPLE_CHOICE - 多选题

```json
{
  "correctAnswer": [
    "A",
    "B",
    "C"
  ],
  "options": [
    {
      "id": "A",
      "text": "1"
    },
    {
      "id": "B",
      "text": "3"
    },
    {
      "id": "C",
      "text": "5"
    },
    {
      "id": "D",
      "text": "6"
    }
  ],
  "id": 1,
  "type": "MULTIPLE_CHOICE",
  "content": "以下哪些数字是单数？",
  "explanation": "1 3 5 7 9 是单数"
}
```

## TRUE_FALSE - 判断题

```json
{
  "correctAnswer": [
    "A"
  ],
  "options": [
    {
      "id": "A",
      "text": "正确"
    },
    {
      "id": "B",
      "text": "错误"
    }
  ],
  "id": 1,
  "type": "TRUE_FALSE",
  "content": "1 是单数",
  "explanation": "1 3 5 7 9 是单数"
}
```

## FILL_IN_BLANK - 填空题

```json
{
  "correctAnswer": [
    "5",
    "1"
  ],
  "options": [
  ],
  "id": 1,
  "type": "FILL_IN_BLANK",
  "content": "数字 0 到 10 之间共有{{}}个单数，其中最小的单数是{{}}。",
  "explanation": "1 3 5 7 9 是单数"
}
```

## SHORT_ANSWER - 问答题

```json
{
  "correctAnswer": [
    "1 3 5 7 9 是单数"
  ],
  "options": [
  ],
  "id": 1,
  "type": "SHORT_ANSWER",
  "content": "请简述你知道的10以内的单数",
  "explanation": "1 3 5 7 9 是单数"
}
```
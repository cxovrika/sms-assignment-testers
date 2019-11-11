# მეთოდოლოგიების ტესტერები

```sh
# გადმოწერა / კლონირება
git clone git@github.com:cxovrika/sms-assignment-testers.git
# ცვლილებების განახლება
git pull --rebase # გაუშვით კლონირებულ დირექტორიაში
```

## შემადგენლობა
თითოეულის ინსტრუქცია წერია ან აქვე, ან დირექტორიის README.md-ში


### testers
- თითოეული დავალების ტესტერი
- changecommands.sh სკრიპტი, რომელიც ვინდოუსისთვის დაწერილ commands.bat-ს გადააკონვერტირებს ლინუქსზე. Find&replace 
არის ბრძანებებზე და სხვა სინტაქსზე, შემოწმება უნდა გამოყენების შემდეგ 

### assignment checkers
ხელით რომ არ მოგიწიოთ ამ ყველაფრის გაკეთება, ლინუქსისთვის სკრიპტი დავწერე, რომელიც ავტომატურად
1. ყველა .rar-ს ექსტრაქტს გაუკეთებს 
2. თითოეულისთვის ტესტერს გაუშვებს და შედეგს შეინახავს

### issues
აქ გადავიტანე კონტეინერიზაციის პროგრესი  და დოკერის ფაილები

## კონტაქტი
- [ ] TODO გითჰაბის გამოყენება პრობლემების შესაგროვებლად

რაიმე იდეა, რჩევა, ხშირი პრობლემა თუ გექნებათ, ჩაიწერეთ სადმე და შევაგროვებთ ერთად. თუ სასწრაფოა რამე და დავალებას ვერ ასწორებთ ამ ტექნიკური ხარვეზის გამო, დაგვიკავშირდით პირადად: 

#### ტესტერების სიზუსტე - ელენე
ყველაფერი, რაც წერია .java ფაილებში. თუ რამე ტესტი არასწორად ან არ მუშაობს, etc.

#### ტესტერების გაშვება
- windows .bat ფაილები - ელენე
- linux .sh ფაილები - ია
- ავტომატური შემმოწმებელი - ია 

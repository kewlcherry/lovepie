require 'faker'
require 'machinist'
require 'machinist/active_record'

User.blueprint do
  login { Faker::Name.name }
  email { Faker::Internet.email }
  password {'loremipsum'}
  password_confirmation {'loremipsum'}
end

Organisation.blueprint do
  name { Faker::Name.name }
  paypal { Faker::Internet.email }
  url "http://test.com"
  active true
end

Cause.blueprint do
  name { Faker::Name.name }
  organisation
  user
end
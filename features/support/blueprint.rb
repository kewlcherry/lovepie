require 'faker'
require 'machinist'
require 'machinist/active_record'

Sham.name { Faker::Name.name }
Sham.email { Faker::Internet.email }
Sham.password { Faker::Name.first_name }

Sham.define do
  login {Faker::Name.name }
  email {Faker::Internet.email }
end

User.blueprint do
  login { Sham.login }
  email { Sham.email }
  password {'loremipsum'}
  password_confirmation {'loremipsum'}
end

Organisation.blueprint do |o|
  o.name Sham.name
  o.paypal Sham.email
  o.url "http://test.com"
  o.active true
end

Cause.blueprint do
  name Sham.name
end


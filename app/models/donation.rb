class Donation < ActiveRecord::Base
  belongs_to :user
  has_many :donation_percentages
  has_many :causes, :through => :donation_percentages
end

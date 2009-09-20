class DonationPercentage < ActiveRecord::Base
  belongs_to :donation
  belongs_to :cause
end

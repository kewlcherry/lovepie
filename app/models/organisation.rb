class Organisation < ActiveRecord::Base
    acts_as_taggable_on :causes
end